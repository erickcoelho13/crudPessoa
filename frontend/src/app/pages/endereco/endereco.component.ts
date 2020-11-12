import { Component, OnInit } from '@angular/core';
import { EnderecoService } from '../../services/endereco.service';
import { Endereco } from '../../models/endereco';
import { Pessoa } from '../../models/pessoa';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router'; 


@Component({
  selector: 'app-master',
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.css']
})
export class EnderecoComponent implements OnInit {

  endereco = {} as Endereco;
  pessoa = {} as Pessoa;
  enderecos: Endereco[];

  constructor(private enderecoService: EnderecoService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      //this.endereco = this.enderecos[+params.get('id')];
      this.getEnderecosByPessoa(+params.get('id'));
    });
  }

  // define se um endereco será criado ou atualizado
  saveEndereco(form: NgForm) {
    if (this.endereco.id !== undefined) {
      this.enderecoService.updateEndereco(this.endereco).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.route.paramMap.subscribe(params => {
        this.enderecoService.saveEndereco(this.endereco, params.get('id')).subscribe(() => {
          this.cleanForm(form);
        });
      });
      
    }
  }

  // Chama o serviço para obtém todos as enderecos
  getEnderecos() {
    this.enderecoService.getEnderecos().subscribe((enderecos: Endereco[]) => {
      this.enderecos = enderecos;
    });
  }

  // Chama o serviço para obter todos os enderecos
  getEnderecosByPessoa(id: number) {
    this.enderecoService.getEnderecosByPessoa(id).subscribe((enderecos: Endereco[]) => {
      this.enderecos = enderecos;
    });
  }

  // deleta um endereco
  deleteEndereco(endereco: Endereco) {
    this.enderecoService.deleteEndereco(endereco).subscribe(() => {
      this.getEnderecosByPessoa(endereco.pessoa_id);
    });
  }

  // copia o endereco para ser editado.
  editEndereco(endereco: Endereco) {
    this.endereco = { ...endereco };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    this.route.paramMap.subscribe(params => {
      this.getEnderecosByPessoa(+params.get('id'));
    });
    form.resetForm();
    this.endereco = {} as Endereco;
  }

}