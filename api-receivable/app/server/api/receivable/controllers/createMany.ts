import { Request, Response, NextFunction } from 'express';
import { AbstractController } from '../../../common/controllers/abstractController';
import { createManyIdvUseCase } from '../repository';
import { CreateManyReceivableUseCaseInterface } from '../repository/interface/createManyReceivableUseCaseInterface';
import httpStatus from 'http-status';

class CreateManyController implements AbstractController {
  
  private method = 'createReceivable';
  private useCase: CreateManyReceivableUseCaseInterface;
  
  constructor(useCaseInterface: CreateManyReceivableUseCaseInterface) {
    this.useCase = useCaseInterface;
  }

  run = async (req: Request, res: Response, next: NextFunction): Promise<void> => {
    try {          
      const response = await this.useCase.createMany(req.body);
      console.log('Request', { method: this.method, data: { url: req.originalUrl } });
      res.status(httpStatus.OK).json(response);
    } catch (error) {
      error.method = this.method;      
      next(error);
    }
  };
}

export default new CreateManyController(createManyIdvUseCase);
