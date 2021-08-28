import os
import json
import time
import calendar
from datetime import datetime


def get_current_datetime():
    return datetime.now()


def get_current_datetime_formatted():
    return get_current_datetime().strftime('%Y-%m-%d %H:%M:%S')


def current_millitime():
    return round(time.time() * 1000)


def get_pwd_dir():
    pwd_dir = os.environ.get('PWD')
    if pwd_dir is None:
        pwd_dir = ''
    elif not pwd_dir.endswith('/app'):
        pwd_dir = pwd_dir + '/app'
    return pwd_dir


def split_array(lst, n):
    return [lst[i:i + n] for i in range(0, len(lst), n)]


def current_timestamp():
    gmt = time.gmtime()
    timestamp = calendar.timegm(gmt)
    return timestamp


def write_json(filename, data):
    with open(filename, 'w') as outfile:
        json.dump(data, outfile)


def get_total_invoice(unitPrice, quantity):
    return unitPrice*quantity


def get_total_igv(total_invoice):
    return round(0.18*total_invoice, 2)
