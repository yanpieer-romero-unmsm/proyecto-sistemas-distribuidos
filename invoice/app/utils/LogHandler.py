import logging
import sys

root = logging.getLogger()
root.setLevel(logging.INFO)

ch = logging.StreamHandler(sys.stdout)
ch.setLevel(logging.INFO)
formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(name)s -  %(funcName)s -  %(message)s')
ch.setFormatter(formatter)
root.addHandler(ch)

logging.getLogger('kafka').setLevel(logging.WARNING)
