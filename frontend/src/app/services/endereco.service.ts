import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Endereco } from '../models/endereco';

@Injectable({
  providedIn: 'root'
})
export class EnderecoService {

  url = 'http://localhost:8080/enderecos'; // api rest

  // Injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  // Obtem todos os enderecos
  getEnderecos(): Observable<Endereco[]> {
    return this.httpClient.get<Endereco[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem todos endereços pelo id da pessoa
  getEnderecosByPessoa(id: number): Observable<Endereco[]> {
    return this.httpClient.get<Endereco[]>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // Obtem um endereco pelo id
  getEnderecoById(id: number): Observable<Endereco> {
    return this.httpClient.get<Endereco>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // Salva um endereco
  saveEndereco(endereco: Endereco, id: string): Observable<Endereco> {
    return this.httpClient.post<Endereco>(this.url + '/' + id, JSON.stringify(endereco), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // Atualiza um endereco
  updateEndereco(endereco: Endereco): Observable<Endereco> {
    return this.httpClient.put<Endereco>(this.url + '/' + endereco.id, JSON.stringify(endereco), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Deleta um endereco
  deleteEndereco(endereco: Endereco) {
    return this.httpClient.delete<Endereco>(this.url + '/' + endereco.id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };
}