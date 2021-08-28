# Dictionary Microsystem

Project assumptions:

Containerized application supporting the universal dictionary microsystem.

    1. Database (mysql engine) contains tables dict_conf (dictionaries definition) and dict_item (dictionary items definition).
        Dictionaries can be related to each other. One can be a subdictionary of another. Dictionary item can be an alias of another item.
    2. Backend, RestAPI:
        - dictionaries list
        - dictionary items
        - add / edit / activate / deactivate a dictionary
        - add / edit / activate / deactivate a position
        - simple registry
        - batch processing (activating and deactivating dictionary or items) starting from API (test -> webhook.site)
        - batch processing termination
    3. Web application:
        - dictionaries list with actions: add / archive / delete 
        - directory items with actions like above
    4. Additional information:
        - Db component as database schema
        - Backend and web app are separate containers

MySql schema:
    
	\CONFIG-EXAMPLE\mysql_schema.sql
	\CONFIG-EXAMPLE\mysql_schema_with_example_data.sql

    or have to:
        - create user with DDL & DML permissions
        - create database "dictionary" 
        - application creates schema on startup

    or run this docker compose:
  
    version: "3.7"
    services:
      db:
        image: mysql:8.0.26
        container_name: home-db
        restart: always
        ports:
          - "8880:3306"
        networks:
          - dictionary-microsystem-network
        environment:
          MYSQL_ROOT_PASSWORD: {ROOT_PASSWORD}
          MYSQL_DATABASE: dictionary
          MYSQL_USER: {USERNAME}
          MYSQL_PASSWORD: {PASSWORD}
    networks:
      dictionary-microsystem-network:
      external: true

Docker images: 
	
	Rest WebService: https://hub.docker.com/r/gsw91/d-ws2:1.1
	Web Application: https://hub.docker.com/r/gsw91/d-ws2_app:1.1


Docker pre configuration for application:

    1. Create network for containers: dictionary-microsystem-network 
    2. Add this network to mysql container
    3. Create volume for files processing and copy there the content from /CONFIG-EXAMPLE/file-procesing/
        a. Container dir /file-processing/ contains files to process (on webhook call)
		b. Container dir /file-processing/archive/ contains the processed files
		c. Files type must be .csv with three columns incuding header, example:
			type;id;setActive
			item;18;0
			dict;10;0
			item;19;0
			dict;3;1

Docker compose:

- after startup
    - api (swagger): http://${HOST}:8080/DictionaryWebService/swagger-ui/#/
    - client: http://${HOST}:8081/dms


    version: "3.7"

    services:

        dictionary-rest-service:
            image: gsw91/d-ws2:1.1
            container_name: dictionary-rest-service
            ports:
                - "8080:8080"
            environment:
                APP_DB_CONNECTION_STRING: {example: jdbc:mysql://home-db/dictionary?serverTimezone=Europe/Warsaw&useSSL=False}
                APP_DB_USER: {USER}
                APP_DB_PASSWORD: {PASSWORD}
            networks:
                - dictionary-microsystem-network
            volumes:
                - dictionary-files:/app/file-processing
            restart: unless-stopped
    
        dictionary-web-client:
            image: gsw91/d-ws2_app:1.1
            container_name: dictionary-web-app
            ports:
                - "8081:8081"
            environment:
                REST_BASE_URL: http://dictionary-rest-service:8080
            networks:
                - dictionary-microsystem-network
            restart: unless-stopped
            depends_on:
                - dictionary-rest-service

    networks:
        dictionary-microsystem-network:
            external: true

    volumes:
        dictionary-files:
            external: true