# dockchain

I just try to clarify blockchain principles to myself. Someday this just might be
nice dockerized blockchain playground. But we're pretty far from it :)

You can actually send messages to another node from repl.

Start redis for communication (docker compose only starts redis for now)
```
docker-compose up
```

Start "dummy node"

```
cd node
lein run
```

Start "active node"

```
cd node
lein repl
...
...
dockchain.core=> (-main)
dockchain.core=> (broadcast-new-message "Seppo" "Seppo sez hi!")
```
