defmodule ChatService.Application do
  use Application
  require Logger

  @default_port 4000

  @impl true
  def start(_type, _args) do
    port =
      if System.get_env("PORT"),
        do: String.to_integer(System.get_env("PORT")),
        else: @default_port

    Logger.info("Starting application listen on port #{port}...")

    children = [
      Plug.Cowboy.child_spec(
        scheme: :http,
        plug: ChatService.Router,
        options: [
          dispatch: dispatch(),
          port: port
        ]
      ),
      Registry.child_spec(
        keys: :duplicate,
        name: Registry.ChatService
      )
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: ChatService.Supervisor]
    Supervisor.start_link(children, opts)
  end

  defp dispatch do
    [
      {:_,
       [
         {"/ws/[...]", ChatService.SocketHandler, []},
         {:_, Plug.Cowboy.Handler, {ChatService.Router, []}}
       ]}
    ]
  end
end
