import logging
import requests

import constants.constants as constants

logger = logging.getLogger(__name__)


def post(url, headers={}, **kwargs):
    logger.info(f'URL: {url}')
    logger.info(f'Headers: {headers}')
    if constants.REQ_JSON in kwargs:    
        data = kwargs[constants.REQ_JSON]
        logger.info(f'Request Body: {data}')

    r = requests.post(url, headers=headers, **kwargs)
    logger.info(r)
    return generate_response(r)


def get(url, headers={}, **kwargs):
    logger.info(f'URL: {url}')
    logger.info(f'Headers: {headers}')

    r = requests.get(url, headers=headers, **kwargs)
    return generate_response(r)


def generate_response(r):
    status_code = r.status_code
    logger.info(f'Status Code: {status_code}')
    return status_code
