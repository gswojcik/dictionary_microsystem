docker container rm DictionaryWebApp
docker run -e "app.base_url=http://000.000.0.0:8080" ^
	-d ^
	-p 8989:8989 ^
	--name DictionaryWebApp ^
	gsw91/d-ws2_app:latest
pause