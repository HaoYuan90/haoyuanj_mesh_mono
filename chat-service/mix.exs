defmodule ChatService.MixProject do
  use Mix.Project

  def project do
    [
      app: :chat_service,
      version: "0.1.0",
      elixir: "~> 1.18",
      start_permanent: Mix.env() == :prod,
      deps: deps()
    ]
  end

  # Run "mix help compile.app" to learn about applications.
  def application do
    [
      extra_applications: [:logger],
      mod: {ChatService.Application, []}
    ]
  end

  # Run "mix help deps" to learn about dependencies.
  defp deps do
    [
      {:cowboy, "~> 2.4"},
      {:jason, "~> 1.1"},
      {:plug_cowboy, "~> 2.0"},
      {:plug, "~> 1.7"}
    ]
  end
end
