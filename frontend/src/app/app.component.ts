import { Component, OnInit } from '@angular/core';
import { PessoaService } from './services/pessoa.service';
import { Pessoa } from './models/pessoa';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  pessoa = {} as Pessoa;
  pessoas: Pessoa[];

  constructor(private pessoaService: PessoaService) {}
  
  ngOnInit() {
    this.getPessoas();
  }

  // Define se um pessoa será criado ou atualizado
  savePessoa(form: NgForm) {
    if (this.pessoa.id !== undefined) {
      this.pessoaService.updatePessoa(this.pessoa).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.pessoaService.savePessoa(this.pessoa).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }

  // Chama o serviço para obtém todos as pessoas
  getPessoas() {
    this.pessoaService.getPessoas().subscribe((pessoas: Pessoa[]) => {
      this.pessoas = pessoas;
    });
  }

  // Deleta uma pessoa
  deletePessoa(pessoa: Pessoa) {
    this.pessoaService.deletePessoa(pessoa).subscribe(() => {
      this.getPessoas();
    });
  }

  // Copia uma pessoa para ser editado.
  editPessoa(pessoa: Pessoa) {
    this.pessoa = { ...pessoa };
  }

  // Limpa o formulario
  cleanForm(form: NgForm) {
    this.getPessoas();
    form.resetForm();
//    pessoa = {} as Pessoa;
  }

}