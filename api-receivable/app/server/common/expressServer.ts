import express, { Application } from 'express';
import actuator from 'express-actuator';
import path from 'path';
import http from 'http';
import os from 'os';
import cookieParser from 'cookie-parser';
import 'reflect-metadata';

import installValidator from './openapi';
import cors, { CorsOptionsDelegate } from 'cors';

const app = express();
const exit = process.exit;

const corsOptionsDelegate: CorsOptionsDelegate = (req, callback) => {
  const corsOptions = { origin: true };
  callback(null, corsOptions);
};

export default class ExpressServer {
  private routes!: (app: Application) => void;
  constructor() {
    const root = path.normalize(__dirname + '/../..');
    app.set('appPath', root + 'client');
    app.use(express.json({ limit: process.env.REQUEST_LIMIT || '100kb' }));
    app.use(
      express.urlencoded({
        extended: true,
        limit: process.env.REQUEST_LIMIT || '100kb',
      })
    );
    app.use(express.text({ limit: process.env.REQUEST_LIMIT || '100kb' }));
    app.use(cookieParser(process.env.SESSION_SECRET));
    app.use(actuator());
    app.use(express.static(`${root}/public`));
    app.use(cors(corsOptionsDelegate));
  }

  router(routes: (app: Application) => void): ExpressServer {
    this.routes = routes;    
    return this;
  }

  listen(port: number): Application {
    const welcome = (p: number) => (): void => {
      console.log(`up and running in ${process.env.ENVIRONMENT || 'development'} @: ${os.hostname()} on port: ${p}}`);
    };

    installValidator(app, this.routes)
      .then(() => {
        http.createServer(app).listen(port, welcome(port));
      })
      .catch((e) => {
        console.log(e);
        exit(1);
      });

    return app;
  }
}
