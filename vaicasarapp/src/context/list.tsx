import React, { createContext, useState, useContext, ReactNode } from 'react';
import { DayProps } from '../components/Calendar';

interface ProviderProps {
  children: ReactNode;
}

export interface StoreProps {
  name: string;
  category: string;
}
export interface TaskProps {
  id: number | null;
  title?: string | '';
  scheduledDate?: DayProps | null;
  conclusionDate?: DayProps | null;
  store?: StoreProps | null;
  finished: boolean;
  description?: string;
  value?: string;
}

export interface TaskList {
  list: TaskProps[];
  setList: React.Dispatch<React.SetStateAction<TaskProps[]>>;
}

const TaskContext = createContext({} as TaskList);

export default function TaskProvider({ children }: ProviderProps) {
  const [list, setList] = useState<TaskProps[]>([
    {
      id: 0,
      title: 'Buffet',
      scheduledDate: {
        day: 8,
        month: 11,
        year: 2022,
        timestamp: 1667916108000,
        dateString: '8/11/2022',
      },
      conclusionDate: null,
      store: { name: 'Patricia Xavier', category: 'Buffet' },
      finished: false,
      description: '',
      value: '',
    },
    {
      id: 1,
      title: 'Cerimônia',
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'Patricia Xavier', category: 'Cerimônia' },
      finished: false,
      description: 'Fazer tal coisa',
      value: '',
    },
    {
      id: 2,
      title: 'Festa',
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'Ana Monteiro', category: 'Decoração' },
      finished: false,
      description: 'Fazer tal coisa',
      value: '',
    },
    {
      id: 3,
      title: 'Lua de Mel',
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'CVC', category: 'Viagem' },
      finished: false,
      description: 'Fazer tal coisa',
      value: '',
    },
    {
      id: 4,
      title: 'Música',
      scheduledDate: null,
      conclusionDate: null,
      store: { name: 'David Guetta', category: 'DJ' },
      finished: false,
      description: 'Fazer tal coisa',
      value: '',
    },
  ]);

  return (
    <TaskContext.Provider value={{ list, setList }}>
      {children}
    </TaskContext.Provider>
  );
}

export function useTask() {
  const context = useContext(TaskContext);

  const { list, setList } = context;

  return { list, setList };
}
