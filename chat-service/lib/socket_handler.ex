defmodule ChatService.SocketHandler do
  require Logger
  @behaviour :cowboy_websocket

  @spec message_id(map()) :: String.t() | nil
  def message_id(msg_payload) do
    cond do
      !Map.has_key?(msg_payload, "data") ->
        nil

      !Map.has_key?(msg_payload["data"], "message") ->
        nil

      !Map.has_key?(msg_payload["data"]["message"], "message_id") ->
        nil

      true ->
        msg_payload["data"]["message"]["message_id"]
    end
  end

  def ack_message(message_id) do
    %{
      "data" => %{
        "ack" => %{
          "message_id" => message_id,
          "timestamp_mili" => DateTime.to_unix(DateTime.utc_now(), :millisecond)
        }
      }
    }
  end

  def init(request, _state) do
    state = %{registry_key: request.path}

    {:cowboy_websocket, request, state}
  end

  def websocket_init(state) do
    Registry.ChatService
    |> Registry.register(state.registry_key, {})

    {:ok, state}
  end

  @spec websocket_handle(
          {:text,
           binary()
           | maybe_improper_list(
               binary() | maybe_improper_list(any(), binary() | []) | byte(),
               binary() | []
             )},
          any()
        ) :: {:reply, {:text, any()}, any()}
  def websocket_handle({:text, json_payload}, state) do
    Logger.info("Received message: #{json_payload}")

    try do
      payload = Jason.decode!(json_payload)
      message_id = message_id(payload)
      ack_message = Jason.encode!(ack_message(message_id))

      Registry.ChatService
      |> Registry.dispatch(state.registry_key, fn entries ->
        for {pid, _} <- entries do
          if pid != self() do
            Process.send(pid, json_payload, [])
          end
        end
      end)

      {:reply, {:text, ack_message}, state}
    rescue
      _e in Jason.DecodeError ->
        Logger.error("Failed to decode message")
        {:reply, {:text, "Invalid payload"}, state}
    end
  end

  def websocket_info(info, state) do
    {:reply, {:text, info}, state}
  end
end
