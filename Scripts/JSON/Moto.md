# API - Moto

## GET
**Endpoint** -> /moto

## GetById
**Endpoint** -> /moto/1

## POST
**Endpoint** -> /moto
````
{
	"placa":"HDP5L29",
	"chassi":"9C6KE1110PC012345",
	"modeloMoto":"MOTTU_SPORT",
	"ano":2023,
	"statusMoto":"ATIVA",
	"filial":1	
}
````

## PUT
**Endpoint** -> /moto/1
````
{
	"placa":"HDP5L29",
	"chassi":"9C6KE1110PC012345",
	"modeloMoto":"MOTTU_SPORT",
	"ano":2023,
	"statusMoto":"INATIVA",
	"filial":1	
}
````

## DELETE
**Endpoint** -> /moto/1