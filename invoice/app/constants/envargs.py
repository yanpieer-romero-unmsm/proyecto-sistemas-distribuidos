import os


def get_env_var(key, default=''):
    value = os.getenv(key, default)
    if value == '':
        raise Exception(f'No se encontró la variable de entorno: {key}')
    return value

KAFKA_SERVER = get_env_var('KAFKA_SERVER')
KAFKA_PORT = get_env_var('KAFKA_PORT')
KAFKA_POLL_TIMEOUT = get_env_var('KAFKA_POLL_TIMEOUT', '1000')
KAFKA_POLL_MAXRECORDS = get_env_var('KAFKA_POLL_MAXRECORDS', '5000')

KAFKA_POLL_TIMEOUT = int(KAFKA_POLL_TIMEOUT)
KAFKA_POLL_MAXRECORDS = int(KAFKA_POLL_MAXRECORDS)

KAFKA_TOPIC = get_env_var('KAFKA_TOPIC')
KAFKA_TOPIC_OUT = get_env_var('KAFKA_TOPIC_OUT')
