# API - Posição Patio

## GET
**Endpoint** -> /posicao-patio

## GetById
**Endpoint** -> /posicao-patio/1

## POST
**Endpoint** -> /posicao-patio
````
{
	"identificacao":"A2",
	"numeroLinha":1,
	"numeroColuna":2,
	"area":"MINHA_MOTTU",
	"ocupado":true,
	"filial":1	
}
````

## PUT
**Endpoint** -> /posicao-patio/1
````
{
	"identificacao":"A1",
	"numeroLinha":1,
	"numeroColuna":1,
	"area":"MINHA_MOTTU",
	"ocupado":false,
	"filial":1	
}
````

## DELETE
**Endpoint** -> /posicao-patio/1