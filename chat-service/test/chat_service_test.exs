defmodule ChatServiceTest do
  use ExUnit.Case
  doctest ChatService

  test "greets the world" do
    assert ChatService.hello() == :world
  end
end
