ACME FILE ACCESS
=================

###Objetivo do Projeto

	O Seguinte projeto tem como objetido de listar, renomar e realizar o upload de arquivos para o AWS S3.
	
	
###Tecnologias

	Este Projeto faz uso das seguintes tecnologias:
	
		1 - Spring Boot na versão 2.0.4.RELEASE
		
		2 - AngularJS na versão v1.6.9
		
		3 - Bootstrap na versão v4.0.0
		
		4 - aws-java-sdk na versão 1.11.386
		
###Adicional

	Este projeto contem o Dockertfile para build da aplicação
	

###Arquitetura

	Arquitetura monilítica simples com front end e backe end no mesmo build.
	
	
###DockerFile

	Antes do Build da aplicação, deve-se imputar as chaves de acesso ao AWS S3 em:
	
		ENV ACME_ACCESS_KEY_ID <ACCESS KEY>
		
		ENV ACME_SECRET_ACCESS_KEY <SECRET KEY>
	
	
###Build

	Para Build da aplicação execute os seguintes passos:
	
		1 - No mesmo nivel de diretório do Dockerfile execute **"docker build -t <<usuarioDockerHub>>/acme:latest ."**.
		
		2 - Liste as imagens para obter o ID com o seguinte comando __"docker images"__.
		
		3 - Execute o container com o seguinte comando __"docker container run -d -p 8081:8081 <idimage>"__, lembre-se de imputar o ID da Imagem.
		
	

