from repository.connection import Conexion
from json import dumps


class daoInvoice():
    def save_invoice(invoice_id, total_igv, total_invoice, client, articles):
        conexion = Conexion()
        query = """INSERT INTO invoices (invoice_id, total_invoice, total_igv, client, articles) VALUES (%s, %s, %s, %s, %s)"""
        con = conexion.getConexion()
        cursor = con.cursor()
        cursor.execute(query, (invoice_id, total_invoice, total_igv, client, articles))
        con.commit()
        conexion.closeConexion()
