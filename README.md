# PlayTest

Simple rest framework proof of concept.
Returns data about market surveys.

In order to run this you need java installed only (any version above 1.6.0.45)

### Technology stack
* JDK : 1.6.0.45
* PlayFramework 2.0 : compatible with jdk6
* Gson 2.2.2

### REST
<pre>
* localhost:9000                              | REST endpoints
* localhost:9000/metadata                     | get info about the available surveys
* localhost:9000/metadataByTopic?topic=       | get info about surveys of a specific topic
* localhost:9000/count                        | number of surveys available for each topic
* localhost:9000/survey?id=                   | get the survey, survey ID can be extracted from metadata or metadataByTopic requests
</pre>

### To run the service locally (distro included) :
<code><pre>
cd CLID/dist
unzip clid-1.0-SNAPSHOT.zip 
cd clid-1.0-SNAPSHOT
chmod 777 start
./start
</code></pre>

### Development deploy
Download and install play 2.0 framework and run : 
<code><pre>
cd CLID
play run
</code></pre>






