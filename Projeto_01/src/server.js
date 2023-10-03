//import http = require('http') Padrão CommonJS de import usando require 
import http from 'node:http' // Padrão ESmodule usando o import/export 
import { json } from './middleware/json.js' //sempre que trabalhamos com o "type": "module" no arquivo package.json,
import { routes } from './routes.js'
import { extractQueryParams } from './utils/extract-query-params.js'
//é necessário na importação colocar a extensão do arquivo, como no exemplo './middleware/json.js'

const server = http.createServer(async (req, res) => {

    const { method, url } = req

    await json(req, res) 

    const route = routes.find(route => {
        return route.method === method && route.path.test(url)
    })

    if(route) {
        const routeParams = req.url.match(route.path)

        const { query, ...params } = routeParams.groups

        req.params = params
        req.query = query ? extractQueryParams(query) : {}

        return route.handler(req, res)
    } 
    
    return res.writeHead('404').end()
})

server.listen(3333)
//localhots:3333