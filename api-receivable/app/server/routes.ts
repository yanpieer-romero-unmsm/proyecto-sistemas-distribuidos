import { Application } from 'express';
import receivableRouter from './api/receivable/controllers/router';

export default function routes(app: Application): void {
  app.use(`${process.env.BASE_PATH}receivable`, receivableRouter);
}
