function  A  = read_file( file_name )

fid = fopen(file_name);

tline = fgetl(fid);
i=1;
while ischar(tline)
    A{i}=tline;
    i=i+1;
    tline = fgetl(fid);
end

fclose(fid);

end

