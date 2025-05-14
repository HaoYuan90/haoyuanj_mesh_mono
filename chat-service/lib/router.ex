defmodule ChatService.Router do
  use Plug.Router

  plug(:match)
  plug(:dispatch)

  get "/" do
    send_resp(conn, 200, "Home")
  end

  get "/join" do
    send_resp(conn, 200, "Welcome")
  end

  get "/leave" do
    send_resp(conn, 200, "Bye")
  end

  match _ do
    send_resp(conn, 404, "Not found")
  end
end
