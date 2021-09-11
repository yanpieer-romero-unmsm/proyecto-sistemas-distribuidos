import { ReceivableEntity } from '../../entities/receivableEntity';

export interface CreateManyReceivableUseCaseInterface {
  createMany(
    idvs: ReceivableEntity
  ): Promise<{ created: String }>;
}
