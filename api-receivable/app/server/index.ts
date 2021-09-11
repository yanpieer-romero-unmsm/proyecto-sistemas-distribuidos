import './common/env';
import ExpressServer from './common/expressServer';
import routes from './routes';

const port = parseInt(process.env.PORT || '3000');
export default new ExpressServer().router(routes).listen(port);
