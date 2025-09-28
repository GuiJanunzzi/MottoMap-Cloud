# API - Filial

## GET
**Endpoint** -> /filial

## GetById
**Endpoint** -> /filial/1

## POST
**Endpoint** -> /filial
````
{
	"nome":"Mottu Rio",
	"endereco":"Avenida Hilario Pereira de Sousa, 406",
	"cidade":"Osasco",
	"siglaEstado":"SP",
	"numeroColuna":"10",
	"numeroLinha":"10",
	"capacidadeMaxima":"100"
}
````

## PUT
**Endpoint** -> /filial/1
````
{
	"nome":"Mottu Londrina",
	"endereco":"Avenida das Flores, 210",
	"cidade":"Londrina",
	"siglaEstado":"PR",
	"numeroColuna":"20",
	"numeroLinha":"30",
	"capacidadeMaxima":"600"
}
````

## DELETE
**Endpoint** -> /filial/1