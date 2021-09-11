import pgRepository from '../../../common/services/persistance/typeorm/pgRepository';
import { CreateManyReceivableUseCase } from './createManyUseCase';

export const createManyIdvUseCase = new CreateManyReceivableUseCase(pgRepository);