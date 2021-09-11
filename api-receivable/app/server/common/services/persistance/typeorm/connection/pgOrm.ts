import path from 'path';
import { Connection, ConnectionOptions, createConnection } from 'typeorm';

export class PgORM {
  private dbconnPromise: Promise<Connection>;
  private options: ConnectionOptions;

  constructor() {    
    this.options = this.getConfig(
      process.env.DB_POSTGRE_USER_CL || '',
      process.env.DB_POSTGRE_NAME_CL || '',
      process.env.DB_POSTGRE_PASS_CL || '',
      process.env.DB_POSTGRE_HOST_CL || '',
      Number(process.env.DB_POSTGRE_PORT_CL) || 5432
    );
    this.dbconnPromise = this._connect();
  }
  
  public async waitConnect(): Promise<Connection> {
    return await this.dbconnPromise;
  }

  private _connect(): Promise<Connection> {
    return new Promise(async (resolve, reject) => {
      try {
        const connection = await createConnection(this.options);
        if (connection) {
          resolve(connection);
        } else {
          throw new Error('Error creating connection');
        }
      } catch (error) {
        reject(error);
      }
    });
  }

  private getConfig(
    user: string,
    database: string,
    password: string,
    host: string,
    port: number
  ): any {
    const schemaPath = path.join(__dirname, '../schema/*{.ts,.js}');
    const commonParams = {
      type: 'postgres',
      synchronize: false,
      logging: false,
      entities: [schemaPath]
    };
    const customParams = {    
      username: user,
      database: database,
      password: password,
      host: host,
      port: port,
    };
    const params = Object.assign(customParams, commonParams);
    return customParams;
  }
}
