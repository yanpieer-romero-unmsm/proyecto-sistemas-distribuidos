import { Connection } from 'typeorm';
import { PgORM } from './pgOrm';

export class OrmConnections {
  private static _instance: OrmConnections;
  private connections: PgORM;
  constructor() {
    this.connections = new PgORM();
  }
  static getInstance(): OrmConnections {    
    if (!OrmConnections._instance) {
      OrmConnections._instance = new OrmConnections();
    }
    return OrmConnections._instance;
  }
  public async connect(): Promise<Connection> {
      console.log(`connected Postgre database successfully`);
       
    const connection = await this.connections.waitConnect()
    return connection;
  }
}
