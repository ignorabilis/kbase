FROM clojure:alpine

LABEL "owner"="Cristian Joe" \
      "developer"="Irina Yaroslavova Stefanova"

COPY . /opt/kbase-src

WORKDIR /opt/kbase-src

RUN apk update \
    # needed because some fonts are missing and java is complaining
    && apk add ttf-dejavu \
    && apk add nodejs \
    && apk add nodejs-npm \
    # needed because builds may fail in some of the zeit.co regions
    && npm config set unsafe-perm true \
    && npm install --save-dev shadow-cljs \
    && npm install -g shadow-cljs \
    && npm install \
    && shadow-cljs release main \
    && lein uberjar

WORKDIR /opt

RUN mv kbase-src/target/kbase.jar ./kbase.jar \
    && rm -rf kbase-src \
    && apk del nodejs-npm \
    && apk del nodejs

EXPOSE 8080

CMD java \
        -jar \
        kbase.jar
