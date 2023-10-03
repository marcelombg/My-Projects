import { Database } from "./database.js"
import { randomUUID } from "node:crypto"
import { buildRoutePath } from './utils/build-route-path.js'
import { formatInTimeZone } from 'date-fns-tz';

const database = new Database()

export const routes = [
    {
        method: 'GET',
        path: buildRoutePath('/tasks'),
        handler: (req, res) => {

            const { search } = req.query

            const users = database.select('users', search ? {
                title: search,
                description: search,
                created_at: search,
                updated_at: search,
                completed_at: search
            } : null)

            return res.end(JSON.stringify(users))
        }

    },
    {
        method: 'POST',
        path: buildRoutePath('/tasks'),
        handler: (req, res) => {
            const { title, description } = req.body

            const user = {
                id: randomUUID(),
                title,
                description,
                created_at: formatInTimeZone(new Date(), 'America/Sao_Paulo', 'yyyy-MM-dd HH:mm:ssXXX'),
                updated_at: formatInTimeZone(new Date(), 'America/Sao_Paulo', 'yyyy-MM-dd HH:mm:ssXXX'),
                completed_at: null
            }

            database.insert('users', user)

            return res.writeHead('201').end()
        }
    },
    {
        method: 'PUT',
        path: buildRoutePath('/tasks/:id'),
        handler: (req, res) => {
            const { id } = req.params
            const { title, description } = req.body

            if (!title && !description) {
                res.status(400).send('Os campos "title" e/ou "description" devem ser fornecidos.')
                return
            }

            const existingTasks = database.select('users', { id });

            if (!existingTasks || existingTasks.length === 0) {
                res.status(404).send('Tarefa não encontrada.')
                return
            }

            const taskToUpdate = existingTasks.find(task => task.id === id);

            if (!taskToUpdate) {
                res.status(404).send('Tarefa não encontrada.')
                return
            }

            if (title && !description) {
                taskToUpdate.title = title;
            } else if (description && !title) {
                taskToUpdate.description = description;
            } else {
                res.status(400).send('Você só pode atualizar o "title" ou o "description", não ambos.');
                return;
            }

            database.update('users', id, taskToUpdate);

            return res.writeHead(204).end();
        }
    },
    {
        method: 'DELETE',
        path: buildRoutePath('/tasks/:id'),
        handler: (req, res) => {
            const { id } = req.params
            const envio = req.body

            database.delete('users', id)

            return res.writeHead(204).end()
        }
    },
    {
        method: 'PATCH',
        path: buildRoutePath('/tasks/:id/complete'),
        handler: (req, res) => {
            const { id } = req.params;
    
            const existingTasks = database.select('users', { id });
    
            if (!existingTasks || existingTasks.length === 0) {
                return res.status(404).send('Tarefa não encontrada.');
            }
    
            const taskToUpdate = existingTasks.find(task => task.id === id);
    
            if (!taskToUpdate) {
                res.status(404).send('Tarefa não encontrada.');
            } else {
                taskToUpdate.completed_at = new Date().toISOString();
                database.update('users', id, taskToUpdate);
            }

            return res.writeHead(204).end();
        }
    }    
]