CNCF Meetup Lorient : Améliorer la vitesse des échanges entre vos
services avec gRPC
========
Le format gRPC est un très bon candidat pour la mise en place d'API internes entre services ou micro services quel que soit le langage. Lors de cette présentation, je souhaite vous expliquer quelle est l'idée qui se cache derrière, quel en sont les intérêts et vous faire une petite démonstration de sa facilité de mise en œuvre entre un service Java et un service Python.

[Slides de la présentation](Presentation.pdf)

Python
=======

Il faut créer un virtual env et installer les dépendances:
```
cd python
virtualenv ~/virtenv/grpc
source ~/virtenv/grpc/bin/activate
pip install -r requirement.txt 
```

La génération des stubs se fait via la ligne de commande suivante: 
```
python3 -m grpc_tools.protoc --proto_path=../java/src/main/proto/ --python_out=. --grpc_python_out=. ../java/src/main/proto/cncf.proto
```

Protobuf
=======

Pour protobuff, il faut avoir protoc dans le path, [pour cela se reporter directement à documentation protocol buffer](https://developers.google.com/protocol-buffers).

La génération des stubs se fait via la commande suivante :
```
protoc --java_out=src/main/generated/ src/main/proto/hello.proto
```

Sources
=======

gRPC :
  * [Présentation gRPC par expectra](https://medium.com/expedia-group-tech/introducing-grpc-to-our-hotels-com-platform-part-1-61716af50b13)
  * [Présentation gRPC à la Pycon 2019](https://pyvideo.org/pycon-fr-2019/grpcpython-exemple-pratique-dutilisation-de-micro-services-pour-une-mini-application-danalyse-semantique-de-textes-npl.html)
  * [Billet de blog introduisant gRPC en 2015](https://grpc.io/blog/principles/)
  * [Gestion des erreurs dans gRPC](https://www.baeldung.com/grpcs-error-handling)
  * [Présentation gRPC par Scaleway](https://www.youtube.com/watch?v=r3xdCYP9mVs)

Protocol Buffer: 
  * [Documentation sur l'encodage de Protobuf](https://protobuf.dev/programming-guides/encoding/)
  * [Billet Infoq lors de création de Protocbuf en 2008](https://www.infoq.com/news/2008/07/google-protocol-buffers/)
  * [Comparaison Thrift avec Protobuff](https://stuartsierra.com/2008/07/10/thrift-vs-protocol-buffers)
  * [Discution stack overflow sur sint / int](https://stackoverflow.com/questions/765916/is-there-ever-a-good-time-to-use-int32-instead-of-sint32-in-google-protocol-buff)

HTTP 2 :
  * [La RFC 7540](https://httpwg.org/specs/rfc7540.html)
  * [HTTP2 explained](https://http2-explained.haxx.se/fr/part2)
  * [Analyzing HTTP2 with Wireshark](https://celaldogan2010.medium.com/analyzing-http-2-with-wireshark-64c15793e91)
