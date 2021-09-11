import { ReceivableEntity } from '../../../api/receivable/entities/receivableEntity';

export interface PersistanceInterface {
  createManyReceivable(receivable: ReceivableEntity): Promise<String | undefined>;
}
