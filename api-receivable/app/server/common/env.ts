import * as dotenv from 'dotenv';

dotenv.config();

const requiredVariables = [
  'BASE_PATH',
  'PORT',
  'LOG_LEVEL',
  'OPENAPI_SPEC',
  'DB_POSTGRE_NAME_CL',
  'DB_POSTGRE_SCHEMA_CL',
  'DB_POSTGRE_USER_CL',
  'DB_POSTGRE_PASS_CL',
  'DB_POSTGRE_HOST_CL',
  'DB_POSTGRE_PORT_CL',
];

for (let i = 0; i < requiredVariables.length; i += 1) {
  const envVariable = requiredVariables[i];
  if (!process.env[envVariable]) {
    throw new Error(`The env variables ${envVariable} is not set`);
  }
}

const config = {};

export = config;
