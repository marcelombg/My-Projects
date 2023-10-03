import fs from 'fs/promises';
import { parse } from 'csv-parse';
import axios from 'axios';

async function importCSV() {
  try {
    const fileContents = await fs.readFile('tasks.csv', 'utf-8');
    const parser = parse(fileContents, { columns: true });

    parser.on('data', async (row) => {
      try {
        const response = await axios.post('http://localhost:3333/tasks', {
          title: row.title,
          description: row.description,
        });
        console.log('Tarefa importada com sucesso:', response.data);
      } catch (error) {
        console.error('Erro ao importar tarefa:', error.message);
      }
    });

    parser.on('end', () => {
      console.log('Importação concluída.');
    });
  } catch (error) {
    console.error('Erro ao ler o arquivo CSV:', error.message);
  }
}

importCSV();