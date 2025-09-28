# API - Problema

## GET
**Endpoint** -> /problema

## GetById
**Endpoint** -> /problema/1

## POST
**Endpoint** -> /problema
````
{
	"tipoProblema": "LEGAL",
	"descricao": "Placa apagada",
	"dataRegistro": "18/05/2025",
	"resolvido": false,
	"idMoto":1,
	"idUsuario":1
}
````

## PUT
**Endpoint** -> /problema/1
````
{
	"tipoProblema": "LEGAL",
	"descricao": "Placa apagada",
	"dataRegistro": "18/05/2025",
	"resolvido": true,
	"idMoto":1,
	"idUsuario":1
}
````

## DELETE
**Endpoint** -> /problema/1