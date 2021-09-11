import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Generic } from '../models/generic';

export abstract class CommonService<M extends Generic> {

  protected baseEndpoint: string;

  protected headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(protected http: HttpClient) { }

  public listar(): Observable<M[]> {
     return this.http.get<M[]>(this.baseEndpoint);
  }

  public listarByObject(): Observable<M> {
    return this.http.get<M>(this.baseEndpoint);
 }


  public obtener(id: string): Observable<M> {
    return this.http.get<M>(`${this.baseEndpoint}/${id}`)
  }

  public crear(model: M): Observable<M> {
    return this.http.post<M>(
      this.baseEndpoint,
      model,
      {headers: this.headers}
    );
  }

  public editar(model: M): Observable<M> {
    return this.http.put<M>(
      `${this.baseEndpoint}/${model.id}`,
      model,
      {headers: this.headers}
    );
  }

  public eliminar(id: number): Observable<void>{
    return this.http.delete<void>(`${this.baseEndpoint}/${id}`);
  }

}