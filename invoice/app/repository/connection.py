import pymysql


class Conexion():

    def __init__(self):
        self.con = None

    def getConexion(self):
        try:
            if self.con is None:
                credenciales = {'host':'localhost', 'user':'root', 'password':'romerosalazar', 'database':'invoice'}
                self.con = pymysql.connect(**credenciales)
                print('Conexión establecida')
            return self.con
        except:
            print('Error al intentar establecer la conexión')

    def closeConexion(self):
        try:
            self.con.rollback()
            self.con.close()
            print('Conexión cerrada')
        except:
            print('Error al intentar cerrar la conexión')

    def commitConexion(self):
        try:
            self.con.commit()
            print('Update comprobado')
        except:
            print('Error al comprobar update')