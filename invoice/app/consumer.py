from multiprocessing import Process

import constants.envargs as envargs
import constants.constants as constants
#import utils.Utils as Utils
import streaming.KafkaReader as kafkaReader


if __name__ == '__main__':

    # get the list of topics that can be processed
    topics = envargs.KAFKA_TOPIC.split(',')

    # create parameter json file
   # Utils.create_parameters(constants.PARAMETERSJSON)    
    for topic in topics:
        # for each topic creates a kafka reader and consume
        p = Process(target=kafkaReader.consume, args=(topic,))
        p.start()
