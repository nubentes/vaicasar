import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import {
  createNativeStackNavigator,
  NativeStackScreenProps,
} from '@react-navigation/native-stack';

import Home from '../screens/Home';
import Task from '../screens/Task';
import { TaskProps } from '../context/list';

export type RootStackParamList = {
  navigate(screen: string, obj: { task: TaskProps; type: string }): unknown;
  Home: undefined;
  Task: { task: TaskProps; type: string };
};

export type Props = NativeStackScreenProps<RootStackParamList, 'Task'>;

const Stack = createNativeStackNavigator<RootStackParamList>();

export default function Routes() {
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Home" component={Home} />
        <Stack.Screen name="Task" component={Task} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
