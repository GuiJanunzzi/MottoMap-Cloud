# API - Usuário

## GET
**Endpoint** -> /usuario

## GetById
**Endpoint** -> /usuario/1

## POST
**Endpoint** -> /usuario
````
{
	"nome":"João Silva",
	"email":"joao.silva@mottomap.com",
	"senha":"Senha12345@",
	"cargoUsuario":"ADM_GERAL",
	"filial":"1"
}
````

## PUT
**Endpoint** -> /usuario/1
````
{
	"nome":"João Silva Santos",
	"email":"joaoss@mottomap.com",
	"senha":"Joao@321",
	"cargoUsuario":"COL_MECANICO",
	"filial":"1"
}
````

## DELETE
**Endpoint** -> /usuario/1