import { Connection } from 'typeorm';
import { PersistanceInterface } from '../persistanceInterface';
import { OrmConnections } from './connection/ormConnections';
import { Receivable } from './schema/receivable';
import { types } from 'pg';
import { getShippingDate } from '../../../utils';
import { ReceivableEntity } from '../../../../api/receivable/entities/receivableEntity';

types.setTypeParser(20, function(val: any) {
  return parseInt(val);
});

types.setTypeParser(1700, function(val: any) {
  return parseFloat(val);
});

class PgRepository implements PersistanceInterface {

  public async createManyReceivable(receivable: ReceivableEntity): Promise<String | undefined> {

    console.log(receivable)
    console.log(receivable["receivable_id"])

    const connection: Connection = await OrmConnections.getInstance().connect();    
    const queryRunner = connection.createQueryRunner();
    await queryRunner.connect();    

    try {
      async function insertReceivable(): Promise<String> {
        let receivableToRetrieve: String = '';
        try {
          await queryRunner.startTransaction();
          await queryRunner.manager
            .getRepository(Receivable)
            .createQueryBuilder()
            .insert()
            .into(Receivable)
            .values([
              {
                receivable_id: receivable["receivable_id"],
                invoice_id: receivable["invoice_id"],
                shipping_date: getShippingDate(),
                total_igv: receivable["total_igv"],
                total_invoice: receivable["total_invoice"],
                client: receivable["client"],
                articles: receivable["articles"]
              }                
            ])
            .execute();                                      
          await queryRunner.commitTransaction();
          receivableToRetrieve = receivable['receivable_id'];
        } catch (error) {
          if (queryRunner && queryRunner.isTransactionActive) await queryRunner.rollbackTransaction();
          console.log('Error Single Transaction Create', {
            method: 'PostgresService.createMany',
            data: {
              receivableToRetrieve,
            },
            error: error.message,
          });
        }
        return receivableToRetrieve;
      }          
      const result = await insertReceivable();
      return result; 
    } catch (error) {
      if (queryRunner.isTransactionActive) await queryRunner.rollbackTransaction();
      console.log('Error CreateMany Query', {
        method: 'PostgresService.createMany',
        error: error,
      });
      return undefined;
    } finally {
      if (queryRunner && !queryRunner.isReleased) await queryRunner.release();
    }
  } 
}
const pgRepository = new PgRepository();
export default pgRepository;
