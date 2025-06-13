FROM elixir:otp-26-alpine

EXPOSE 8080
ENV PORT=8080

ENV MIX_ENV=prod

COPY . .
RUN mix deps.get
RUN mix release

CMD ["/_build/prod/rel/chat_service/bin/chat_service",  "start"]