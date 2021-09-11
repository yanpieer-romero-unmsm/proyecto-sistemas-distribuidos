import * as path from 'path';
import express, { Application } from 'express';
import { OpenApiValidator } from 'express-openapi-validator';
import errorHandler from './middlewares/errorHandler';
export default function(app: Application, routes: (app: Application) => void): Promise<void> {
  const apiSpec = path.join(__dirname, 'api.yml');
  const validateResponses = !!(
    process.env.OPENAPI_ENABLE_RESPONSE_VALIDATION &&
    process.env.OPENAPI_ENABLE_RESPONSE_VALIDATION.toLowerCase() === 'true'
  );
  return new OpenApiValidator({
    apiSpec,
    validateResponses,
  })
    .install(app)
    .then(() => {
      app.use(process.env.OPENAPI_SPEC || '/spec', express.static(apiSpec));
      routes(app);
      app.use(errorHandler);
    });
}
