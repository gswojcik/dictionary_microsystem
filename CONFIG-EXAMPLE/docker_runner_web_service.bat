docker container rm DictionaryWebService
docker volume rm dws-config
docker container create --name DictionaryWebService -v dws-config:/dws-config ^
	--mount type=bind,source=C:/Users/gwojcik/Pulpit/project_dictionary/ws/file-processing,target=/file-processing ^
	-p 8080:8080 gsw91/d-ws2:latest
docker cp dws-config/dws-config.properties DictionaryWebService:/dws-config/dws-config.properties
docker start DictionaryWebService 
pause 
