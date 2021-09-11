import { Column } from 'typeorm';

export class BaseTable {

  @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP(6)' })
  created_at?: Date;

  @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP(6)' })
  payment_date?: Date;

  @Column({ type: 'timestamp'})
  shipping_date?: Date;
}
