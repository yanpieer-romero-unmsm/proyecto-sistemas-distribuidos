import { Column, Entity, PrimaryColumn } from 'typeorm';
import { BaseTable } from './helpers/base';

@Entity({
  name: 'receivables',
})
export class Receivable extends BaseTable {
   
  @PrimaryColumn()
  receivable_id?: string;
  
  @Column()
  invoice_id?: string;  

  @Column()
  total_invoice?: number; 

  @Column()
  total_igv?: number;
  
  @Column({ type: 'jsonb'})
  client?: object; 

  @Column({ type: 'jsonb'})
  articles?: object;
}
