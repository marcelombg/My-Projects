import { Knex } from "knex";

export async function up(knex: Knex): Promise<void> {
    await knex.schema.createTable('user', (table) => {
        table.uuid('id').primary();
        table.text('name').notNullable(); 
        table.text('email').notNullable(); 
    });
}

export async function down(knex: Knex): Promise<void> {
    await knex.schema.dropTable('user');
}