import express from 'express';
import createManyController from './createMany';

export default express
  .Router()
  .post('/', createManyController.run)
