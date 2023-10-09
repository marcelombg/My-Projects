import { Knex } from "knex";

declare module 'knex/types/tables' {
    export interface Tables {
        user: {
            id: string,
            name: string,
            email: string,
            session_id?: string
        },
        meal: {
            id: string,
            name: string,
            description: string,
            datetime: string,
            diet: boolean,
            session_id?: string
        }
    }
}