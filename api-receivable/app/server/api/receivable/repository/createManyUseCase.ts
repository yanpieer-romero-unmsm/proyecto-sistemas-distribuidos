import { PersistanceInterface } from '../../../common/services/persistance/persistanceInterface';
import { ReceivableEntity } from '../entities/receivableEntity';
import { CreateManyReceivableUseCaseInterface } from './interface/createManyReceivableUseCaseInterface'

export class CreateManyReceivableUseCase implements CreateManyReceivableUseCaseInterface {

  private storage: PersistanceInterface;

  constructor(storageImpl: PersistanceInterface) {
    this.storage = storageImpl;    
  }

  public async createMany(
    receivable: ReceivableEntity
  ): Promise<{ created: String }> {
    
   // const idvErrors: { message: string; code: string; payload: ReceivableEntity }[] = []; 

    const productStoragePG: String | undefined = await this.storage.createManyReceivable(receivable);
    if (!productStoragePG) {
      throw Error('Error retrieving Receivable from Storage PostgreSQL');
    }    

    return { created: productStoragePG };    
  }
    
}
