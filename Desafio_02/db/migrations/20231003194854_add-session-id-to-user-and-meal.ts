import { Knex } from "knex";


export async function up(knex: Knex): Promise<void> {
    await knex.schema.alterTable('user', (table) => {
        table.uuid('session_id').after('id').index();
    });
    await knex.schema.alterTable('meal', (table) => {
        table.uuid('session_id').after('id').index();
    });
}


export async function down(knex: Knex): Promise<void> {
    await knex.schema.alterTable('user', (table) => {
        table.dropColumn('session_id');
    });
    await knex.schema.alterTable('meal', (table) => {
        table.dropColumn('session_id');
    });
}

