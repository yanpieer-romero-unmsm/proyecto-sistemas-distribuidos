import { NextFunction, Request, Response } from 'express';
import * as errorEntities from '../entities/error';
import httpStatus from 'http-status';

export default function errorHandler(err: any, req: Request, res: Response, next: NextFunction): void {
  const response: errorEntities.Error = {
    status: err.status || httpStatus.INTERNAL_SERVER_ERROR,
    message: `Error description: ${err.message}`,
  };

  console.log(`Error: ${err.message}`, {
    method: err.method ? err.method : 'errorHandler',
    data: err.stack,
  });

  res.status(err.status || 500).send(response);
  next();
}
