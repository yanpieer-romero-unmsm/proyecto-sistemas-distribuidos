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
    elif constants.REQ_FILES in kwargs:
        data = kwargs[constants.REQ_FILES]
        logger.info(f'Request Filename: {data}')

    r = requests.post(url, headers=headers, **kwargs)
    return generate_response(r)


def get(url, headers={}, **kwargs):
    logger.info(f'URL: {url}')
    logger.info(f'Headers: {headers}')

    r = requests.get(url, headers=headers, **kwargs)
    return generate_response(r)


def generate_response(r):
    status_code = r.status_code
    data = r.json()
    result = {
        constants.RES_STATUS_CODE: status_code,
        constants.RES_DATA: data
    }
    logger.info(f'Status Code: {status_code}')
    logger.debug(f'Body: {data}')
    return result
